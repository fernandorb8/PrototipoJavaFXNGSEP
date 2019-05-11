/**
 * 
 */
package ngsepfx.controller;

import java.net.URL;

import ngsepfx.event.NGSEPEvent;

/**
 * @author fernando
 *
 */
public class VCFFilterController extends AnalysisAreaController {

	/* (non-Javadoc)
	 * @see ngsepfx.controller.AnalysisAreaController#getCSSExternalForm()
	 */
	@Override
	public String getCSSExternalForm() {
		// TODO Auto-generated method stub
		return getClass().getResource("/ngsepfx/view/vcffilter.css")
				.toExternalForm();
	}

	/* (non-Javadoc)
	 * @see ngsepfx.controller.AnalysisAreaController#getFXMLResource()
	 */
	@Override
	public URL getFXMLResource() {
		// TODO Auto-generated method stub
		return getClass().getResource("/ngsepfx/view/VCFFilter.fxml");
	}

	/* (non-Javadoc)
	 * @see ngsepfx.controller.AnalysisAreaController#handleNGSEPEvent(ngsepfx.event.NGSEPEvent)
	 */
	@Override
	public void handleNGSEPEvent(NGSEPEvent event) {
		// TODO Auto-generated method stub

	}

}
