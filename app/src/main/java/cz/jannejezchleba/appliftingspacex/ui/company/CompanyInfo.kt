package cz.jannejezchleba.appliftingspacex.ui.company

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cz.jannejezchleba.appliftingspacex.R
import cz.jannejezchleba.appliftingspacex.data.model.Company
import cz.jannejezchleba.appliftingspacex.ui.component.InfoItemColumn
import cz.jannejezchleba.appliftingspacex.ui.component.InfoItemRow
import cz.jannejezchleba.appliftingspacex.ui.component.SocialsIconButton
import cz.jannejezchleba.appliftingspacex.ui.theme.CustomMaterialTheme

@Composable
fun CompanyInfo(company: Company) {
    Column(modifier = Modifier.fillMaxSize()) {
        val logo = painterResource(R.drawable.spacex)
        Image(
            modifier = Modifier
                .aspectRatio(logo.intrinsicSize.width / logo.intrinsicSize.height)
                .fillMaxWidth(),
            contentScale = ContentScale.Fit,
            painter = logo,
            contentDescription = stringResource(id = R.string.DESC_SPACEX)
        )
        Column(verticalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .padding(CustomMaterialTheme.padding.L)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(CustomMaterialTheme.padding.S)
            ) {

                InfoItemRow(stringResource(R.string.COMPANY_CEO), company.ceo)
                InfoItemRow(stringResource(R.string.COMPANY_COO), company.coo)
                InfoItemRow(stringResource(R.string.COMPANY_FOUNDED), company.founded)
                InfoItemRow(
                    stringResource(R.string.COMPANY_VALUATION),
                    "$${company.getFormattedValuation()} ${stringResource(R.string.UNIT_BILLION)}"
                )
                InfoItemRow(
                    stringResource(R.string.COMPANY_EMPLOYEES),
                    company.employees.toString()
                )
                InfoItemColumn(stringResource(R.string.COMPANY_HQ), company.getFullLocation())
                InfoItemColumn(stringResource(R.string.COMPANY_SUMMARY), company.summary)

            }

            BoxWithConstraints(
                modifier = Modifier.padding(CustomMaterialTheme.padding.S),
                contentAlignment = Alignment.Center
            ) {
                SocialsRow(company.links)
            }
        }
    }
}


@Composable
fun SocialsRow(links: Company.Links) {
    val context = LocalContext.current
    val websiteIntent = remember { Intent(Intent.ACTION_VIEW, Uri.parse(links.website)) }
    val flickrIntent = remember { Intent(Intent.ACTION_VIEW, Uri.parse(links.flickr)) }
    val twitterIntent = remember { Intent(Intent.ACTION_VIEW, Uri.parse(links.twitter)) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        links.website.let {
            SocialsIconButton(
                R.drawable.web,
                stringResource(id = R.string.DESC_LINK_WEBSITE),
                context,
                websiteIntent
            )
        }
        links.flickr.let {
            SocialsIconButton(
                R.drawable.flickr,
                stringResource(id = R.string.DESC_LINK_FLICKR),
                context,
                flickrIntent
            )
        }
        links.twitter.let {
            SocialsIconButton(
                R.drawable.twitter,
                stringResource(id = R.string.DESC_LINK_TWITTER),
                context,
                twitterIntent
            )
        }
    }
}